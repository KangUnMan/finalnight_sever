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

    const menu = document.querySelector('.menu');
    const menucontainer = document.querySelector('.menu_container');
    menu.addEventListener('click', function(event){
        event.preventDefault();
        menucontainer.classList.toggle('active');
    });

    document.addEventListener('click', function(event) {
        // 클릭된 요소가 메뉴 버튼이나 메뉴 컨테이너가 아닌 경우
        if (!menu.contains(event.target) && !menucontainer.contains(event.target)) {
            menucontainer.classList.remove('active'); // active 클래스 제거
        }
    });

    const acc = document.querySelector('.acc2');
    const acccontainer = document.querySelector('.acc_container');
    acc.addEventListener('click', function(event){
        event.preventDefault();
        acccontainer.classList.toggle('active');
    });

    document.addEventListener('click', function(event) {
        // 클릭된 요소가 메뉴 버튼이나 메뉴 컨테이너가 아닌 경우
        if (!acc.contains(event.target) && !acccontainer.contains(event.target)) {
            acccontainer.classList.remove('active'); // active 클래스 제거
        }
    });

    // 스크롤 이벤트 리스너 추가
    window.addEventListener('scroll', handleScroll);
});
