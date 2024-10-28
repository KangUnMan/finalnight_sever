import fetch from 'node-fetch';

const url = 'http://localhost:8080/api/login';
const data = {
    userId: 'yourUserId',
    userPassword: 'yourPassword'
};

fetch(url, {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
})
.then(response => response.json())
.then(data => console.log(data))
.catch(error => console.error('Error:', error));
