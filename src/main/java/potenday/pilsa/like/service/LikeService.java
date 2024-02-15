package potenday.pilsa.like.service;

import com.amazonaws.services.kms.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.global.dto.request.RequestPageDto;
import potenday.pilsa.global.exception.BadRequestException;
import potenday.pilsa.global.exception.ExceptionCode;
import potenday.pilsa.like.domain.Like;
import potenday.pilsa.like.domain.repository.LikeRepository;
import potenday.pilsa.like.dto.response.ResponseLikePilsaListDto;
import potenday.pilsa.member.domain.Member;
import potenday.pilsa.member.domain.Status;
import potenday.pilsa.member.domain.repository.MemberRepository;
import potenday.pilsa.pilsa.dto.response.ResponsePilsaMainListDto;

@Service
@RequiredArgsConstructor
@ToString
@Transactional
@jakarta.transaction.Transactional
public class LikeService {


}
