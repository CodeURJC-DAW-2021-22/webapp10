const showVideo = id => {
    const detailsContainer = document.querySelector('#detailsContainer');
    const { title, videoUrl, description } = lessons.find(lesson => lesson.id === id);

    detailsContainer.innerHTML = `
        <h3>${title}</h3>
        <iframe src="${videoUrl}" class="rounded-2" width="560" height="315" id="videoPlayer" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
        <h4>Description:</h4>
        <p class="mt-3">${description}</p>
    `;
}
