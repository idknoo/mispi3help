sentAjax = function (){
    let restime = document.getElementById("restimeParent").children[0];
    restime.click();
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
async function demo() {
    while(true) {
        await sleep(12000);
        sentAjax();
    }
}
demo();