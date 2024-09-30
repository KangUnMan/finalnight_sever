document.addEventListener('DOMContentLoaded', function() {
    const boxes = document.querySelectorAll('.sec_text, .sec_content, .thr_con1, .thr_con2, .thr_con3, .thr_con4, .thr_text h1, .thr_text h3, .attcard_section li, .spcard_section li, .game_infor_text1, .game_infor_text2');

    function handleScroll() {
        const windowHeight = window.innerHeight;

        boxes.forEach(box => {
            const boxPosition = box.getBoundingClientRect().top;

            // 박스가 화면에 나타났을 때 애니메이션 적용
            if (boxPosition < windowHeight) {
                box.classList.add('visible');
            }
        });
    }

    // 스크롤 이벤트 리스너 추가
    window.addEventListener('scroll', handleScroll);
});
