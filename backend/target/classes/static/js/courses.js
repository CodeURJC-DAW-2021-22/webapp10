const loadMoreButton = document.querySelector('#loadMoreButton');

let page = 1;



const renderTags = tags => {
  const tagsHtml = tags.reduce((html, tag) => {
    return html + `\n <span class="badge bg-primary rounded-pill m-1">${tag}</span>`
  }, "");

  console.log(tagsHtml);

  return tagsHtml;
}

loadMoreButton.addEventListener('click', async event => {
  event.preventDefault();
  event.stopPropagation();

  const coursesContainer = document.querySelector('#coursesContainer');
  const loadMoreContainer = document.querySelector('#loadMoreContainer');

  fetch(`/courses/page?search=${search}&page=${page}`)
    .then(response => response.json())
    .then(({ content, last }) => {
      if (last) loadMoreButton.style.display = 'none';

      content.forEach(({ id, title, description, price, tags }) => {
        const courseElement = document.createElement('div');
        courseElement.classList.add('col');
        courseElement.innerHTML = `
                    <div class="card shadow-sm">
                      <img alt="course-thumbnail" class="bd-placeholder-img card-img-top" width="100%" height="225"
                           src="https://localhost:8443/courses/thumbnail/${id}">
                      <div class="card-body">
                        <div>
                          ${renderTags(tags)}
                        </div>
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
    });
})