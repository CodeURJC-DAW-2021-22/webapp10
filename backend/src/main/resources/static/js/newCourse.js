const form = document.querySelector('#newCourseForm');
const courseTitle = form.querySelector('#courseTitle');
const courseDescription = form.querySelector('#courseDescription');
const coursePrice = form.querySelector('#coursePrice');
const isFreeCourse = form.querySelector('#isFreeCourse');
const courseImage = form.querySelector('#courseImage');
const courseTags = form.querySelector('#courseTags');
const addVideo = document.querySelector('#addVideo');

const tags = [];
const videos = [];

const printTags = (tags) => {
    const tagsContainer = document.querySelector('#tagsContainer');
    tagsContainer.innerHTML = '';

    let totalWidth = 0;
    tags.forEach((tag) => {
        const tagElement = document.createElement('span');
        tagElement.classList.add('badge', 'bg-primary', 'rounded-pill');
        tagElement.style.marginRight = '10px';
        tagElement.innerText = tag;
        tagsContainer.appendChild(tagElement);
        totalWidth += tagElement.clientWidth + 10;
    });

    courseTags.style.paddingLeft = `${totalWidth + 10}px`;
};

courseTags.addEventListener('keyup', (event) => {
    event.preventDefault();
    if (event.key === 'Enter' && courseTags.value.trim() !== '') {
        tags.push(event.target.value);
        event.target.value = '';
        printTags(tags);
        console.log(tags);
    } else if (event.key === ',' || event.key === ' ' && courseTags.value.trim() !== '') {
        tags.push(event.target.value.substring(0, event.target.value.length - 1));
        event.target.value = '';
        printTags(tags);
        console.log(tags);
    }
});

courseTags.addEventListener('keydown', (event) => {
    if (event.key === 'Backspace' && event.target.value === '') {
        tags.pop();
        printTags(tags);
        console.log(tags);
    }
});

courseTitle.addEventListener('input', (event) => {
    const newCourseTitle = document.querySelector('#newCourseTitle');
    console.log('Change!');

    newCourseTitle.innerHTML = `New course ${event.target.value.length > 0 ? `<i>"${event.target.value}"</i>` : ''}`;
});

isFreeCourse.addEventListener('change', (event) => {
    coursePrice.disabled = event.target.checked;
});

form.addEventListener('keydown', (event) => {
    if (event.key === 'Enter') {
        event.preventDefault();
    }
});

form.addEventListener('submit', (event) => {
    event.preventDefault();
    form.classList.add('was-validated');

    if (form.checkValidity() === false) {
        event.stopPropagation();
    } else {
        const newCourse = {
            title: courseTitle.value,
            description: courseDescription.value,
            price: coursePrice.disabled ? 0 : parseInt(coursePrice.value),
            image: courseImage.value,
            tags: tags,
            videos: videos
        };

        console.log(newCourse);
    }
});

const printVideos = () => {
    const videoContainer = document.querySelector('#videosContainer');
    videoContainer.innerHTML = '';

    videos.forEach(({title, description, image, url}) => {
        console.log(videos);

        const videoElement = document.createElement('div');
        videoElement.classList.add('card', 'mb-3');
        videoElement.innerHTML = `
            <div class="card-body">
                <h5 class="card-title">${title}</h5>
                <p class="card-text">${description}</p>
                <a href="${url}" target="_blank" class="btn btn-primary">Watch</a>
            </div>
        `;

        videoContainer.appendChild(videoElement);
    });
}

const addVideoHandler = (event) => {
    event.preventDefault();
    event.stopPropagation();

    const videoTitle = document.querySelector('#videoTitle').value;
    const videoDescription = document.querySelector('#videoDescription').value;
    const videoThumbnail = document.querySelector('#videoThumbnail').files[0];
    const videoURL = document.querySelector('#videoURL').value;

    const newVideo = {
        title: videoTitle,
        description: videoDescription,
        thumbnail: videoThumbnail,
        url: videoURL
    };

    videos.push(newVideo);
    document.querySelector('#createVideo').removeEventListener('click', addVideoHandler);
    printVideos();
    document.querySelector('#newVideoContainer').innerHTML = '';
}

addVideo.addEventListener('click', (event) => {
    const videoContainer = document.querySelector('#newVideoContainer');
    const newVideo = document.createElement('div');
    newVideo.classList.add('card', 'mb-3');
    newVideo.innerHTML = `
        <div class="card-body">
            <h4 class="mb-4">New video</h4>
            <div class="mb-3">
                <label class="form-label" for="videoTitle">Title</label>
                <input type="text" class="form-control" id="videoTitle" required>
                <div class="invalid-feedback">
                    Please provide a title for the video.
                </div>
            </div>
            <div class="mb-3">
                <label class="form-label" for="videoDescription">Description</label>
                <textarea class="form-control" id="videoDescription" rows="3" required></textarea>
                <div class="invalid-feedback">
                    Please provide a description for the video.
               </div>
            </div>
            <div class="mb-3">
                <label class="form-label" for="videoThumbnail">Upload thumbnail</label>
                <input type="file" class="form-control" id="videoThumbnail" required accept=".jpeg,.png,.gif">
                <div class="invalid-feedback">
                    Please provide a thumbnail for the video.
                </div>
            </div>
            <div class="mb-3">
                <label class="form-label" for="videoURL">YouTube URL</label>
                <input type="text" class="form-control" id="videoURL" required>
                <div class="invalid-feedback">
                    Please provide a YouTube video URL.
               </div>
            </div>
            <div class="d-grid gap-2 mt-4">
                <button id="createVideo" class="btn btn-primary mt-2" formnovalidate>Add video</button>
            </div>
        </div>
    `;

    videoContainer.replaceChildren(newVideo);

    const addVideoButton = document.querySelector('#createVideo');
    addVideoButton.addEventListener('click', addVideoHandler);
});
