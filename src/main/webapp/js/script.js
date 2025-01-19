document.addEventListener('DOMContentLoaded', () => {
    const newGameBtn = document.getElementById('newGameBtn');
    const continueGameBtn = document.getElementById('continueGameBtn');
    const logsDiv = document.getElementById('logs');

    newGameBtn.addEventListener('click', () => {
        sendAction('new');
    });

    continueGameBtn.addEventListener('click', () => {
        sendAction('continue');
    });

    function sendAction(action) {
        fetch('game', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `action=${action}`
        })
        .then(response => response.json())
        .then(data => {
            displayLogs(data.logs);
        })
        .catch(error => {
            console.error('Error:', error);
            displayLogs(['Произошла ошибка при запуске игры.']);
        });
    }

   function displayLogs(logs) {
       logsDiv.innerHTML = ''; // Очищаем контейнер перед началом
       logs.forEach((msg, i) => {
           setTimeout(() => {
               logsDiv.innerHTML="";
               logsDiv.innerHTML = msg;
               logsDiv.scrollTop = logsDiv.scrollHeight;
           }, i * 3000);
       });
}
});

