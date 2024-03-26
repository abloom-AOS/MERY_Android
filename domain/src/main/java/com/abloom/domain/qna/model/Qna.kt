package com.abloom.domain.qna.model

import com.abloom.domain.question.model.Question
import com.abloom.domain.user.model.User
import java.time.LocalDateTime

data class Qna(
    val question: Question,
    val createdAt: LocalDateTime,
    val loginUser: User,
    val fiance: User?,
    private val _loginUserAnswer: Answer?,
    private val _fianceAnswer: Answer?,
    private val _loginUserResponse: Response?,
    private val _fianceResponse: Response?
) {

    val questionId: Long = question.id
    val questionContent: String = question.content

    val isLinkedWithFiance: Boolean = loginUser.isLinkedWithFiance

    val isLoginUserAnswered: Boolean = _loginUserAnswer != null
    val loginUserAnswer: String
        get() {
            check(isLoginUserAnswered) { "로그인 유저가 답변했을 때만 로그인 유저의 답변을 조회할 수 있습니다." }
            return _loginUserAnswer!!.value
        }

    val isFianceAnswerLocked: Boolean
        get() {
            check(isLinkedWithFiance) { "피앙세와 연결되어 있을 때만 피앙세 답변이 잠겨있는지 조회할 수 있습니다." }
            return !isLoginUserAnswered
        }
    val isFianceAnswered: Boolean
        get() {
            check(!isFianceAnswerLocked) { "피앙세 답변이 잠겨있지 않을 때만 피앙세가 답변했는지 조회할 수 있습니다." }
            return _fianceAnswer != null
        }
    val fianceAnswer: String
        get() {
            check(isFianceAnswered) { "피앙세가 답변했을 때만 피앙세 답변을 조회할 수 있습니다." }
            return _fianceAnswer!!.value
        }

    val isLoginUserResponseAdditionLocked: Boolean =
        !isLinkedWithFiance || !isLoginUserAnswered || isFianceAnswerLocked || !isFianceAnswered
    val isLoginUserReacted: Boolean
        get() {
            check(!isLoginUserResponseAdditionLocked) { "로그인 유저의 답변 추가가 잠겨있지 않을 때만 로그인 유저가 반응했는지 조회할 수 있습니다." }
            return _loginUserResponse != null
        }
    val loginUserResponse: Response
        get() {
            check(isLoginUserReacted) { "로그인 유저가 반응했을 때만 로그인 유저의 반응을 조회할 수 있습니다." }
            return _loginUserResponse!!
        }

    val isFianceResponseLocked: Boolean = isLoginUserResponseAdditionLocked || !isLoginUserReacted
    val isFianceResponseWaiting: Boolean
        get() {
            check(!isFianceResponseLocked) { "피앙세의 반응이 잠겨있지 않을 때만 피앙세의 반응을 기다리는지 조회할 수 있습니다." }
            return _fianceResponse == null
        }
    val fianceResponse: Response
        get() {
            check(!isFianceResponseWaiting) { "피앙세의 반응을 기다리고 있는 게 아닐 때만 피앙세의 반응을 조회할 수 있습니다." }
            return _fianceResponse!!
        }

    val isResponseResultLocked: Boolean =
        isLoginUserResponseAdditionLocked || !isLoginUserReacted || isFianceResponseLocked || isFianceResponseWaiting
    val responseResult: ResponseResult
        get() {
            check(!isResponseResultLocked) { "반응 결과가 잠겨있지 않을 때만 반응 결과를 조회할 수 있습니다." }
            return ResponseResult.of(loginUserResponse, fianceResponse)
        }

    init {
        if (loginUser.isLinkedWithFiance) requireNotNull(fiance) { "로그인 유저는 연결되어 있기 때문에 fianceUser가 null일리 없습니다." }
        if (_loginUserResponse != null) requireNotNull(_loginUserAnswer) { "로그인 유저의 반응이 null이 아니면 로그인 유저의 답변도 null일리 없습니다." }
        if (_fianceResponse != null) requireNotNull(_fianceAnswer) { "피앙세 반응이 null이 아니면 피앙세 답변도 null일리 없습니다." }
    }
}
