const $ = (id) => document.getElementById(id);

const state = {
  gameId: 1,
  spinType: 0,
  start: null,
  lastSpin: null,
};

const SYMBOL_EMOJI = {
  A: '🍒',
  B: '🍋',
  C: '🍊',
  D: '🍇',
  E: '🔔',
  G: '⭐',
  S: '7️⃣',
  P: '🍍',
};

function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

function symbolToDisplay(sym) {
  if (sym == null) return '';
  return SYMBOL_EMOJI[sym] ?? sym;
}

function setStatus(text, type) {
  const el = $('status');
  el.textContent = text ?? '';
  el.classList.remove('error', 'success');
  if (type) el.classList.add(type);
}

function setBusy(busy) {
  $('spin').disabled = busy;
  $('loadGame').disabled = busy;
  $('gameId').disabled = busy;
  $('spinType').disabled = busy;
  $('stake').disabled = busy;
  $('lines').disabled = busy;
  $('balance').disabled = busy;
}

async function apiGet(path) {
  const res = await fetch(path, { headers: { 'Accept': 'application/json' }});
  const text = await res.text();
  let body;
  try { body = text ? JSON.parse(text) : null; } catch { body = text; }
  if (!res.ok) {
    const msg = body?.message || body?.errors?.join(', ') || text || `HTTP ${res.status}`;
    throw new Error(msg);
  }
  return body;
}

async function apiPost(path, payload) {
  const res = await fetch(path, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
    },
    body: JSON.stringify(payload),
  });
  const text = await res.text();
  let body;
  try { body = text ? JSON.parse(text) : null; } catch { body = text; }
  if (!res.ok) {
    const msg = body?.message || body?.errors?.join(', ') || text || `HTTP ${res.status}`;
    throw new Error(msg);
  }
  return body;
}

function buildEmptyBoard(cols = 5, rows = 3) {
  const board = [];
  for (let r = 0; r < rows; r++) {
    const row = [];
    for (let c = 0; c < cols; c++) row.push('');
    board.push(row);
  }
  return board;
}

function renderBoard(board, winningCells = new Set()) {
  const root = $('board');
  root.innerHTML = '';

  // backend returns board[row][col]
  const rows = board?.length ?? 0;
  const cols = rows ? board[0].length : 0;

  for (let c = 0; c < cols; c++) {
    const reel = document.createElement('div');
    reel.className = 'reel';

    for (let r = 0; r < rows; r++) {
      const cell = document.createElement('div');
      cell.className = 'cell';
      const key = `${r},${c}`;
      if (winningCells.has(key)) cell.classList.add('win');
      cell.textContent = symbolToDisplay(board[r][c]);
      reel.appendChild(cell);
    }

    root.appendChild(reel);
  }
}

function ensureBoardDom(cols, rows) {
  const root = $('board');
  const existingReels = root.querySelectorAll('.reel');
  if (existingReels.length === cols && existingReels[0]?.querySelectorAll('.cell')?.length === rows) {
    return;
  }
  renderBoard(buildEmptyBoard(cols, rows), new Set());
}

function setBoardCellText(row, col, text) {
  const root = $('board');
  const reel = root.querySelectorAll('.reel')[col];
  if (!reel) return;
  const cell = reel.querySelectorAll('.cell')[row];
  if (!cell) return;
  cell.textContent = text;
}

function setSpinning(spinning) {
  const root = $('board');
  root.classList.toggle('spinning', Boolean(spinning));
}

function deriveSymbolPool(start) {
  const reels = start?.reelSet?.reels;
  if (!reels) return Object.keys(SYMBOL_EMOJI);
  const pool = [];
  Object.values(reels).forEach((r) => {
    (r?.symbols ?? []).forEach((s) => {
      if (s != null) pool.push(s);
    });
  });
  return pool.length ? pool : Object.keys(SYMBOL_EMOJI);
}

async function animateSpin(finalBoard, stopDelaysMs = [0, 150, 300, 450, 600]) {
  const rows = finalBoard?.length ?? 3;
  const cols = rows ? (finalBoard[0]?.length ?? 5) : 5;

  ensureBoardDom(cols, rows);
  setSpinning(true);

  const pool = deriveSymbolPool(state.start);
  const pick = () => pool[Math.floor(Math.random() * pool.length)];

  // keep intervals per reel
  const timers = [];
  for (let c = 0; c < cols; c++) {
    const t = setInterval(() => {
      for (let r = 0; r < rows; r++) {
        setBoardCellText(r, c, symbolToDisplay(pick()));
      }
    }, 55);
    timers.push(t);
  }

  // stop reels sequentially and lock in final symbols
  for (let c = 0; c < cols; c++) {
    const delay = stopDelaysMs[c] ?? (c * 150);
    await sleep(550 + delay);
    clearInterval(timers[c]);
    for (let r = 0; r < rows; r++) {
      setBoardCellText(r, c, symbolToDisplay(finalBoard[r][c]));
    }
  }

  setSpinning(false);
}

function computeBet(stake, lines) {
  const s = Number(stake ?? 0);
  const l = Number(lines ?? 0);
  if (!Number.isFinite(s) || !Number.isFinite(l)) return 0;
  return s * l;
}

function updateMetaFromStart(start) {
  $('gameName').textContent = start?.gameSettings?.name ?? '-';
  $('maxLines').textContent = start?.gameSettings?.configData?.maxLines ?? '-';
  $('maxBet').textContent = start?.gameSettings?.configData?.maxBet ?? '-';
}

function populateStakeOptions(stakes) {
  const sel = $('stake');
  sel.innerHTML = '';
  (stakes ?? []).forEach((s) => {
    const opt = document.createElement('option');
    opt.value = String(s);
    opt.textContent = String(s);
    sel.appendChild(opt);
  });
  if (!sel.value && sel.options.length) sel.value = sel.options[0].value;
}

function clampLines(lines, maxLines) {
  const l = Number(lines);
  const max = Number(maxLines);
  if (!Number.isFinite(l) || l < 1) return 1;
  if (Number.isFinite(max) && max > 0) return Math.min(l, max);
  return l;
}

function setResult(spinResponse) {
  const sim = spinResponse?.spinSimulation;
  const result = spinResponse?.spinResult;

  $('tx').textContent = result?.transactionId ?? '-';
  $('totalWin').textContent = result?.totalWin ?? 0;

  const stake = $('stake').value;
  const lines = $('lines').value;
  const bet = computeBet(stake, lines);
  $('totalBet').textContent = bet;
  const net = (Number(result?.totalWin ?? 0) - bet);
  $('net').textContent = net;

  const winLinesEl = $('winLines');
  winLinesEl.innerHTML = '';

  const linesMap = result?.lines || {};
  const entries = Object.entries(linesMap);

  const winningCells = new Set();
  const lineDefs = state.start?.lines ?? [];

  const addLineCells = (lineId) => {
    const idNum = Number(lineId);
    const def = lineDefs.find((l) => Number(l?.id) === idNum);
    const positions = def?.positions;
    const board = sim?.board;
    if (!Array.isArray(positions) || !Array.isArray(board) || !board.length) return;

    const rows = board.length;
    const cols = board[0]?.length ?? 0;
    for (let c = 0; c < Math.min(cols, positions.length); c++) {
      const pos = Number(positions[c]);
      if (!Number.isFinite(pos)) continue;

      // positions are -1,0,1 relative to the center row (as in MathModel lines)
      const r = pos + 1;
      if (r < 0 || r >= rows) continue;
      winningCells.add(`${r},${c}`);
    }
  };
  if (!entries.length) {
    const div = document.createElement('div');
    div.className = 'winLine';
    div.innerHTML = `<span>No winning lines</span><span></span>`;
    winLinesEl.appendChild(div);
  } else {
    entries
      .sort((a, b) => Number(a[0]) - Number(b[0]))
      .forEach(([lineId, amount]) => {
        addLineCells(lineId);
        const div = document.createElement('div');
        div.className = 'winLine';
        div.innerHTML = `<span>Line ${lineId}</span><span>Win ${amount}</span>`;
        winLinesEl.appendChild(div);
      });
  }

  renderBoard(sim?.board ?? buildEmptyBoard(), winningCells);
}

async function loadGame() {
  setBusy(true);
  setStatus('Loading game config...', null);
  try {
    const gameId = Number($('gameId').value);
    state.gameId = gameId;

    const start = await apiGet(`/api/v1/slot/${gameId}/start`);
    state.start = start;

    updateMetaFromStart(start);
    populateStakeOptions(start?.gameSettings?.configData?.stakes ?? [1]);

    const maxLines = start?.gameSettings?.configData?.maxLines ?? 1;
    $('lines').max = String(maxLines);
    $('lines').value = String(clampLines($('lines').value, maxLines));

    // attempt to guess reel count from returned reelSet
    const reels = start?.reelSet?.reels;
    const cols = reels ? Object.keys(reels).length : 5;
    renderBoard(buildEmptyBoard(cols, 3), new Set());

    setStatus('Ready.', 'success');
  } catch (e) {
    setStatus(e.message || String(e), 'error');
  } finally {
    setBusy(false);
  }
}

async function spin() {
  setBusy(true);
  setStatus('Spinning...', null);
  try {
    if (!state.start) await loadGame();

    const gameId = Number($('gameId').value);
    const spinType = Number($('spinType').value);

    const stake = Number($('stake').value);
    const maxLines = state.start?.gameSettings?.configData?.maxLines ?? 1;
    const numLines = clampLines($('lines').value, maxLines);
    $('lines').value = String(numLines);

    const balance = Number($('balance').value);

    const payload = {
      player: {
        selfExcluded: false,
        balance: {
          real: balance,
          bonus: 0,
          bonusWins: 0,
        },
      },
      spin: {
        stake: stake,
        numLines: numLines,
        autoSpin: false,
      },
    };

    const respPromise = apiPost(`/api/v1/slot/${gameId}/spin/${spinType}`, payload);
    const resp = await respPromise;
    state.lastSpin = resp;

    // animate then apply final board + highlights
    const sim = resp?.spinSimulation;
    const result = resp?.spinResult;

    const linesMap = result?.lines || {};
    const entries = Object.entries(linesMap);
    const winningCells = new Set();
    const lineDefs = state.start?.lines ?? [];
    const addLineCells = (lineId) => {
      const idNum = Number(lineId);
      const def = lineDefs.find((l) => Number(l?.id) === idNum);
      const positions = def?.positions;
      const board = sim?.board;
      if (!Array.isArray(positions) || !Array.isArray(board) || !board.length) return;
      const rows = board.length;
      const cols = board[0]?.length ?? 0;
      for (let c = 0; c < Math.min(cols, positions.length); c++) {
        const pos = Number(positions[c]);
        if (!Number.isFinite(pos)) continue;
        const r = pos + 1;
        if (r < 0 || r >= rows) continue;
        winningCells.add(`${r},${c}`);
      }
    };
    entries.forEach(([lineId]) => addLineCells(lineId));

    await animateSpin(sim?.board ?? buildEmptyBoard(), [0, 160, 320, 480, 640]);
    renderBoard(sim?.board ?? buildEmptyBoard(), winningCells);

    // update side panel (tx/win lines/bet/net)
    setResult(resp);

    const totalWin = Number(resp?.spinResult?.totalWin ?? 0);
    const bet = computeBet(stake, numLines);

    if (totalWin > 0) {
      setStatus(`You win ${totalWin}!`, 'success');
    } else {
      setStatus(`No win. (-${bet})`, null);
    }
  } catch (e) {
    setStatus(e.message || String(e), 'error');
  } finally {
    setBusy(false);
  }
}

function init() {
  $('loadGame').addEventListener('click', () => loadGame());
  $('spin').addEventListener('click', () => spin());

  $('lines').addEventListener('input', () => {
    const maxLines = state.start?.gameSettings?.configData?.maxLines ?? Number($('lines').max) ?? 1;
    const clamped = clampLines($('lines').value, maxLines);
    if (String(clamped) !== String($('lines').value)) {
      $('lines').value = String(clamped);
    }
  });

  $('gameId').addEventListener('change', () => {
    state.start = null;
    $('tx').textContent = '-';
    $('totalWin').textContent = '0';
    $('totalBet').textContent = '0';
    $('net').textContent = '0';
    $('winLines').innerHTML = '';
  });

  renderBoard(buildEmptyBoard(), new Set());
  loadGame();
}

init();
