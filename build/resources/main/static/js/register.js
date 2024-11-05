$(document).ready(function() {
    let isUserIdAvailable = false;

    $('#checkUserIdBtn').on('click', function() {
        const userId = $('#userId').val();
        if (userId) {
            $.ajax({
                url: '/api/check-userId',
                type: 'GET',
                data: { userId: userId },
                success: function(response) {
                    if (response) {
                        $('#userIdCheckResult').text('이미 사용 중인 아이디입니다.').css('color', 'red');
                        $('#registerBtn').prop('disabled', true);
                        isUserIdAvailable = false;
                    } else {
                        $('#userIdCheckResult').text('사용 가능한 아이디입니다.').css('color', 'green');
                        $('#registerBtn').prop('disabled', false);
                        isUserIdAvailable = true;
                    }
                },
                error: function() {
                    $('#userIdCheckResult').text('오류가 발생했습니다. 다시 시도해주세요.').css('color', 'red');
                    $('#registerBtn').prop('disabled', true);
                    isUserIdAvailable = false;
                }
            });
        } else {
            $('#userIdCheckResult').text('아이디를 입력해주세요.').css('color', 'red');
            $('#registerBtn').prop('disabled', true);
            isUserIdAvailable = false;
        }
    });

    // 회원가입 버튼 클릭 시 사용 불가능한 상태에서는 전송되지 않도록 제어
    $('#registerForm').on('submit', function(event) {
        if (!isUserIdAvailable) {
            event.preventDefault(); // 폼 제출 막기
            alert('사용 가능한 아이디로 중복 체크를 진행해주세요.');
        }
    });
});
