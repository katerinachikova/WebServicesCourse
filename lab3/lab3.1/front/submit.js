let buttonSubmit = document.querySelector(".input-button");
let input = document.querySelector("input[type='text']");
let p = document.querySelector(".result");

buttonSubmit.addEventListener("click", async (e) => {
    e.preventDefault();
    let data = {message: input.value};
    let response = await fetch('http://localhost:9000/messaging/', {method: 'POST', body: JSON.stringify(data)});
    if (response.ok) {
        p.innerText = "Message sent.";

    } else {
        p.innerText = `Error ${response.status}`;
    }
    input.value = "";
});