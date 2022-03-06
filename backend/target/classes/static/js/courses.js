const loadMoreButton = document.querySelector('#loadMoreButton');

let page = 1;

fetch(`/courses/page?search=${search}&page=${page}`)
    .then(response => response.json())
    .then(courses => {
        if (courses.length === 0) {
            loadMoreButton.style.display = 'none';
        }
    });

loadMoreButton.addEventListener('click', async event => {
    event.preventDefault();
    event.stopPropagation();

    const coursesContainer = document.querySelector('#coursesContainer');
    const loadMoreContainer = document.querySelector('#loadMoreContainer');

    fetch(`/courses/page?search=${search}&page=${page}`)
        .then(response => response.json())
        .then(courses => {
            courses.forEach(({id, title, description, price, tags}) => {
                const courseElement = document.createElement('div');
                courseElement.classList.add('col');
                courseElement.innerHTML = `
                    <div class="card shadow-sm">
                      <img alt="course-thumbnail" class="bd-placeholder-img card-img-top" width="100%" height="225"
                           src="https://localhost:8443/courses/thumbnail/${id}">
                      <div class="card-body">
                        <p class="card-text"><h5>${title}</h5>
                        <span class="spanmin">${description}</span></p>
                        <div class="d-flex justify-content-between align-items-center">
                          <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-outline-secondary">
                            <a class="nav-link" href="/courses/${id}">View</a>
                            </button>
                          </div>
                          <small class="text-muted">$${price}</small>
                        </div>
                      </div>
                    </div>
                `;

                coursesContainer.insertBefore(courseElement, loadMoreContainer);
            });

            page++;

            fetch(`/courses/page?search=${search}&page=${page}`)
                .then(response => response.json())
                .then(courses => {
                    if (courses.length === 0) {
                        loadMoreButton.style.display = 'none';
                    }
                });
        });
})