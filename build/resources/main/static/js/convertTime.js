document.addEventListener('DOMContentLoaded', function() {
    const playtimeElement = document.querySelector('.playtime');
    if (playtimeElement) {
        const totalSeconds = parseInt(playtimeElement.textContent, 10);
        const hours = Math.floor(totalSeconds / 3600);
        const minutes = Math.floor((totalSeconds % 3600) / 60);
        const seconds = totalSeconds % 60;

        playtimeElement.textContent = `${hours}시간 ${minutes}분 ${seconds}초`;
    }
});