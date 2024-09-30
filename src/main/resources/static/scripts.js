document.addEventListener('DOMContentLoaded', function() {
    const link1 = document.querySelector('.link1');
    const link2 = document.querySelector('.link2');
    
    // 첫 번째 페이지(카드 페이지)에서만 실행되는 코드
    const attcardSection = document.querySelector('.attcard_section');
    const spcardSection = document.querySelector('.spcard_section');
    
    if (attcardSection && spcardSection) {
        link1.addEventListener('click', function(event) {
            event.preventDefault();
            link1.classList.add('active');
            link2.classList.remove('active');
            attcardSection.classList.add('active');
            spcardSection.classList.remove('active');
        });

        link2.addEventListener('click', function(event) {
            event.preventDefault();
            link1.classList.remove('active');
            link2.classList.add('active');
            attcardSection.classList.remove('active');
            spcardSection.classList.add('active');
        });
    }

    // 두 번째 페이지(게임 소개 페이지)에서만 실행되는 코드
    const game_infor_text1 = document.querySelector('.game_infor_text1');
    const game_infor_text2 = document.querySelector('.game_infor_text2');

    if (game_infor_text1 && game_infor_text2) {
        link1.addEventListener('click', function(event) {
            event.preventDefault();
            link1.classList.add('active');
            link2.classList.remove('active');
            game_infor_text1.classList.add('active');
            game_infor_text2.classList.remove('active');
        });

        link2.addEventListener('click', function(event) {
            event.preventDefault();
            link1.classList.remove('active');
            link2.classList.add('active');
            game_infor_text1.classList.remove('active');
            game_infor_text2.classList.add('active');
        });
    }

    // 모달 창 코드
    const cards = document.querySelectorAll('.card');
    const modal = document.getElementById('modal');
    const modalImg = document.getElementById('modal-img');
    const closeBtn = document.getElementById('close');

    cards.forEach(card => {
        card.addEventListener('click', (e) => {
            const src = e.target.dataset.zoomSrc;
            if (src) {
                modalImg.src = src;
                modal.style.display = 'flex'; // 모달 창 보이기
            }
        });
    });

    closeBtn.addEventListener('click', () => {
        modal.style.display = 'none'; // 모달 창 숨기기
    });

    window.addEventListener('click', (e) => {
        if (e.target === modal) {
            modal.style.display = 'none'; // 모달 창 숨기기
        }
    });
});