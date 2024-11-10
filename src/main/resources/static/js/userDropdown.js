document.addEventListener("DOMContentLoaded", function() {
    const userDropdown = document.getElementById("userDropdown");
    const dropdownContent = document.getElementById("dropdownContent");

    if (userDropdown) {
        // 마우스를 올렸을 때 드롭다운 표시
        userDropdown.addEventListener("mouseenter", function() {
            dropdownContent.style.display = "block";
        });

        // 마우스를 뗐을 때 드롭다운 숨김
        userDropdown.addEventListener("mouseleave", function() {
            dropdownContent.style.display = "none";
        });

        // 드롭다운 메뉴에 마우스를 올렸을 때 유지
        dropdownContent.addEventListener("mouseenter", function() {
            dropdownContent.style.display = "block";
        });

        // 드롭다운 메뉴에서 마우스를 뗐을 때 숨김
        dropdownContent.addEventListener("mouseleave", function() {
            dropdownContent.style.display = "none";
        });
    }
});
