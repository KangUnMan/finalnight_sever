window.addEventListener('resize', function() {
    const bg = document.querySelector('.sec_bg');
    const currentWidth = window.innerWidth; // 현재 뷰포트의 너비 가져오기
    const text = document.querySelector('.sec_text');
    const h1 = this.document.querySelector('.sec_text h1');

    if (currentWidth <= 1500) {
        // 너비가 1500px 이하일 때 실행할 코드
        const newHeight = Math.max(300, 550 - (550 - 300) * ((1500 - currentWidth) / 1200)); // 최소 높이 설정
        bg.style.height = newHeight + 'px';
    } else {
        // 너비가 1500px 초과일 때 원래 높이로 복원
        bg.style.height = '550px'; // 기본 높이로 설정
    }

    if (currentWidth <= 1000 && 800 < currentWidth) {
        bg.style.height = '700px';
    }

    if (currentWidth <= 800) {
        const newHeight2 = Math.max(500, 700 - (700 - 350) * ((900 - currentWidth) / 600));
        const newFontSize = Math.max(12, 16 - (16 - 12) * ((800 - currentWidth) / 800));
        const h1Size = Math.max(25, 55 - (55 - 25) * ((800 - currentWidth) / 800));
        text.style.fontSize = newFontSize + 'px'; // 폰트 크기 설정
        h1.style.fontSize = h1Size + 'px';
        bg.style.height = newHeight2 + 'px';
    }
    else {
        text.style.fontSize = '16px';
        h1.style.fontSize = '55px';
    }
});

// 페이지 로드 시 초기 높이 설정
window.dispatchEvent(new Event('resize'));
