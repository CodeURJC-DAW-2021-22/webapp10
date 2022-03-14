const form = document.querySelector('#newCourseForm');
const courseTitle = form.querySelector('#courseTitle');
const courseDescription = form.querySelector('#courseDescription');
const coursePrice = form.querySelector('#coursePrice');
const isFreeCourse = form.querySelector('#isFreeCourse');
const courseImage = form.querySelector('#courseImage');
const courseTags = form.querySelector('#courseTags');
const addLesson = document.querySelector('#addLesson');

const tags = [];
const lessons = [];

const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
const csrfToken = document.querySelector('meta[name="_csrf"]').content;

const headers = {
    [csrfHeader]: csrfToken
}

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
    } else if (event.key === ',' || event.key === ' ' && courseTags.value.trim() !== '') {
        tags.push(event.target.value.substring(0, event.target.value.length - 1));
        event.target.value = '';
        printTags(tags);
    }
});

courseTags.addEventListener('keydown', (event) => {
    if (event.key === 'Backspace' && event.target.value === '') {
        tags.pop();
        printTags(tags);
    }
});

courseTags.addEventListener('focusout', (event) => {
    if(event.target.value.length > 0) {
        tags.push(event.target.value);
        event.target.value = '';
        printTags(tags);
    }
});

courseTitle.addEventListener('input', (event) => {
    const newCourseTitle = document.querySelector('#newCourseTitle');

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

const sendCourse = async ({title, description, price, image, tags, lessons}) => {
    const formData = new FormData();
    formData.append('title', title);
    formData.append('description', description);
    formData.append('price', price);
    formData.append('image', image);
    formData.append('tags', JSON.stringify(tags));
    formData.append('lessons', JSON.stringify(lessons));

    return await fetch('/courses/new', {
        method: 'POST',
        headers,
        body: formData
    })
}

form.addEventListener('submit', async (event) => {
    event.preventDefault();
    event.stopPropagation();

    const newLessonContainer = document.querySelector('#newLessonContainer');

    form.classList.add('was-validated');

    if (form.checkValidity() === false) {
        event.stopPropagation();
    } else if(lessons.length === 0) {
        newLessonContainer.innerHTML = `
            <div class="alert alert-danger" role="alert">
                <strong>Error!</strong> You must add at least one lesson.
            </div>
        `;
    } else {
        newLessonContainer.innerHTML = '';
        const res = await sendCourse({
            title: courseTitle.value,
            description: courseDescription.value,
            price: coursePrice.value,
            image: courseImage.files[0],
            tags: tags,
            lessons: lessons
        });

        if(res.status === 200) {
            window.location.href = '/courses';
        } else {
            newLessonContainer.innerHTML = `
                <div class="alert alert-danger" role="alert">
                    <strong>Error!</strong> ${res.statusText}
                </div>
            `;
        }
    }
});

const printLessons = () => {
    const lessonsContainer = document.querySelector('#lessonsContainer');
    lessonsContainer.innerHTML = '';

    lessons.forEach(({title, description, imageId, url}) => {
        const lessonElement = document.createElement('div');
        lessonElement.classList.add('card', 'mb-3', 'p-0');
        lessonElement.innerHTML = `
                    <div class="row g-0">
                        <div class="col-md-4">
                            <img src="https://localhost:8443/image/${imageId}" style="object-fit: cover" class="img-fluid rounded-start h-100" alt="${title}">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">${title}</h5>
                                <p class="card-text">${description}</p>
                                <a href="${url}" class="btn btn-primary">Watch</a>
                            </div>
                        </div>
                    </div>
                `;

        lessonsContainer.appendChild(lessonElement);
    });
}

const addLessonHandler = (event) => {
    const newLessonForm = document.querySelector('#newLessonForm');

    event.preventDefault();
    event.stopPropagation();

    newLessonForm.classList.add('was-validated');

    if (newLessonForm.checkValidity() === false) {
        event.stopPropagation();
    } else {
        const title = document.querySelector('#lessonTitle').value;
        const description = document.querySelector('#lessonDescription').value;
        const lessonThumbnail = document.querySelector('#lessonThumbnail').files[0];
        const url = document.querySelector('#lessonURL').value;

        const formData = new FormData();
        formData.append('image', lessonThumbnail);


        fetch('https://localhost:8443/image/new', {
            method: 'POST',
            headers,
            body: formData
        })
            .then(response => response.json())
            .then(imageId => {
                lessons.push({
                    title,
                    description,
                    imageId,
                    videoUrl: url
                });

                printLessons();
            });

        newLessonForm.removeEventListener('submit', addLessonHandler);
        printLessons();
        document.querySelector('#newLessonContainer').innerHTML = '';
    }
}

addLesson.addEventListener('click', (event) => {
    const lessonContainer = document.querySelector('#newLessonContainer');
    const newLesson = document.createElement('div');
    newLesson.classList.add('card', 'mb-3');
    newLesson.innerHTML = `
        <div class="card-body">
            <form id="newLessonForm" class="needs-validation">
                <h4 class="mb-4">New lesson</h4>
                <div class="mb-3">
                    <label class="form-label" for="lessonTitle">Title</label>
                    <input type="text" class="form-control" id="lessonTitle" required>
                    <div class="invalid-feedback">
                        Please provide a title for the lesson.
                    </div>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="lessonDescription">Description</label>
                    <textarea class="form-control" id="lessonDescription" rows="3" required></textarea>
                    <div class="invalid-feedback">
                        Please provide a description for the lesson.
                   </div>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="lessonThumbnail">Upload thumbnail</label>
                    <input type="file" class="form-control" id="lessonThumbnail" required accept=".jpeg,.jpg,.png,.gif">
                    <div class="invalid-feedback">
                        Please provide a thumbnail for the lesson.
                    </div>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="lessonURL">YouTube URL</label>
                    <input type="text" class="form-control" id="lessonURL" required>
                    <div class="invalid-feedback">
                        Please provide a YouTube video URL.
                   </div>
                </div>
                <div class="d-grid gap-2 mt-4">
                    <button type="submit" class="btn btn-primary mt-2" formnovalidate>Add lesson</button>
                </div>
            </form>
        </div>
    `;

    lessonContainer.replaceChildren(newLesson);

    const newLessonForm = document.querySelector('#newLessonForm');
    newLessonForm.addEventListener('submit', addLessonHandler);
});




