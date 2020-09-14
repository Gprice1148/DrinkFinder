var elementExists = document.getElementById("empty_search_result");

if (elementExists && !isPageBeingRefreshed()) {
    alert('Sorry, there are no search results. Please try again.');
}

function isPageBeingRefreshed() {
    if(performance.navigation.type == performance.navigation.TYPE_RELOAD) {
        return true;
    }else{
        return false;
    }
}