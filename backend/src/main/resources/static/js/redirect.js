const timeOutPromise = (i) => {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            document.querySelector("#redirectTimer").innerHTML = `${i}`;
            resolve();
        }, 1000);
    });
};

const handleTimer = async () => {
    for(let i = 4; i > -1; i--) {
        await timeOutPromise(i);
    }
}

handleTimer().then(() => {
    window.location.href = redirectUrl;
});
