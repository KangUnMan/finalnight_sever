document.addEventListener('DOMContentLoaded', function() {
    const userDropdown = document.getElementById('userDropdown');
    const dropdownContent = document.getElementById('dropdownContent');

    if (userDropdown && dropdownContent) {
        // 마우스를 올렸을 때 드롭다운 보이기
        userDropdown.addEventListener('mouseenter', function() {
            dropdownContent.style.display = 'block';
        });

        // 마우스를 뗐을 때 드롭다운 숨기기
        userDropdown.addEventListener('mouseleave', function() {
            dropdownContent.style.display = 'none';
        });

        // 드롭다운 콘텐츠에 마우스를 올렸을 때 유지하기
        dropdownContent.addEventListener('mouseenter', function() {
            dropdownContent.style.display = 'block';
        });

        // 드롭다운 콘텐츠에서 마우스를 뗐을 때 숨기기
        dropdownContent.addEventListener('mouseleave', function() {
            dropdownContent.style.display = 'none';
        });
    } else {
        console.error("userDropdown 또는 dropdownContent 요소를 찾을 수 없습니다.");
    }
});