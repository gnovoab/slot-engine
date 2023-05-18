package com.gabriel.slot.service;

@SuppressWarnings("PMD.CommentRequired")
public interface ParseService {
    Object objToJson(Object obj);
    Object jsonToObj(String json, Class aClass);
}
