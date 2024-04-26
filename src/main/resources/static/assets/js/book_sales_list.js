document.getElementById('likeButton').addEventListener('click', function () {
    var currentCount = parseInt(document.getElementById('likeCount').innerText);
    var newCount;

    if (this.dataset.liked === 'true') {
        // 좋아요 취소
        newCount = currentCount - 1;
        this.dataset.liked = 'false'; // 좋아요 상태 변경
        document.getElementById('likeIcon').innerHTML = '<i class="far fa-heart"></i>'; // 빈 하트 아이콘
    } else {
        // 좋아요 올라감
        newCount = currentCount + 1;
        this.dataset.liked = 'true'; // 좋아요 상태 변경
        document.getElementById('likeIcon').innerHTML = '<i class="fas fa-heart"></i>'; // 채워진 하트 아이콘
    }

    // 좋아요 수 업데이트
    document.getElementById('likeCount').innerText = newCount;
});