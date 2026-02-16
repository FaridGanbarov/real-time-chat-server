package practice.realtimechatserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.realtimechatserver.dto.request.UserTermRequest;
import practice.realtimechatserver.model.admin.term.UserTerm;
import practice.realtimechatserver.repository.admin.term.UserTermRepository;
import practice.realtimechatserver.service.UserTermService;

@RequiredArgsConstructor
@Service
public class UserTermServiceImpl implements UserTermService {
    private final UserTermRepository userTermRepository;
    @Override
    public UserTerm addTerm(UserTermRequest request) {
        userTermRepository.deleteAll();
        UserTerm userTerm = new UserTerm();
        userTerm.setTerms(request.getTerms());

        return  userTermRepository.save(userTerm);
    }
}
