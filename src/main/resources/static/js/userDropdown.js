document.addEventListener('DOMContentLoaded', function() {
    const userDropdown = document.querySelector('.user-nickname');
    const dropdownContent = document.querySelector('.dropdown-content');

    if (userDropdown && dropdownContent) {
        // 마우스를 올렸을 때 드롭다운 보이기
        userDropdown.addEventListener('mouseenter', function() {
            dropdownContent.classList.add('active');
        });

        // 마우스를 뗐을 때 드롭다운 숨기기
        userDropdown.addEventListener('mouseleave', function() {
            dropdownContent.classList.remove('active');
        });

        // 드롭다운 콘텐츠에 마우스를 올렸을 때 유지하기
        dropdownContent.addEventListener('mouseenter', function() {
            dropdownContent.classList.add('active');
        });

        // 드롭다운 콘텐츠에서 마우스를 뗐을 때 숨기기
        dropdownContent.addEventListener('mouseleave', function() {
            dropdownContent.classList.remove('active');
        });
    } else {
        console.error("userDropdown 또는 dropdownContent 요소를 찾을 수 없습니다.");
    }
});