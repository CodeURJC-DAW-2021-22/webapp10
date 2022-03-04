const showVideo = id => {
    const videoPlayer = document.querySelector('#videoPlayer');
    videoPlayer.src = lessons.find(lesson => lesson.id === id).videoUrl;
}