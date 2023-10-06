package com.sbs.exam.board.vo;

import com.sbs.exam.board.util.Util;
import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.session.Session;

import java.util.Map;

public class Rq {
  public String url;
  public Map<String, String> params;
  public String urlPath;

  public Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(url);
    urlPath = Util.getUrlPathFromUrl(url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }

  public String getParam(String paramName, String defaultValue) {
    if (params.containsKey(paramName) == false) {
      return defaultValue;
    }

    return params.get(paramName);
  }

  public int getIntParam(String paramName, int defaultValue) {
    if (params.containsKey(paramName) == false) {
      return defaultValue;
    }

    try {
      return Integer.parseInt(params.get(paramName));
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public void removeSessionAttr(String key) {
    Session session = Container.getSession();
    session.removeAttribute(key);
  }

  public boolean hasSessionAttr(String key) {
    Session session = Container.getSession();
    return session.hasAttribute(key);
  }

  public void setSessionAttr(String key, Member value) {
    Session session = Container.getSession();
    session.setAttribute(key, value);
  }
}
