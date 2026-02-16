package practice.realtimechatserver.service;

import practice.realtimechatserver.dto.request.UserTermRequest;
import practice.realtimechatserver.model.admin.term.UserTerm;

public interface UserTermService {
    public UserTerm addTerm(UserTermRequest request);
}
