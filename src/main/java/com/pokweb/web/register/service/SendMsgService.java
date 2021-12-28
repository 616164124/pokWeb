package com.pokweb.web.register.service;

import com.pokweb.common.response.WebResponse;

public interface SendMsgService {

    WebResponse sendEmail(String subject,String text,String to,String from);
}
