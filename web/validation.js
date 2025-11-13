let checkX = false;
let checkY = false;
let checkR = false;
let x, y;

const xOptions   = document.getElementById('xOptions');
const inputY = document.getElementById('corY');
const labelX = document.getElementById('labelX');
const labelY = document.getElementById('labelY');
const labelR = document.getElementById('labelR');
const RBlock = document.getElementById('rBlock');
const form = document.getElementById('input-form');
const submitStatus = document.getElementById('submitStatus')
const resTable = document.getElementById('res-table');

function validateNumberInRange(val, start, end) {
    const tmp = val.trim();
    const floatRe = /^[+-]?\d+(\.\d+)?$/;
    if(!floatRe.test(tmp)) return false;
    const num = parseFloat(val);
    return !isNaN(num) && num >= start && num <= end;
}

function NumberOfXSelected() {
    return document.querySelectorAll('#xOptions input[type="checkbox"][name="x"]:checked').length;
}

function validateY() {
    const yStart = -5;
    const yEnd = 5;
    inputY.addEventListener('input', () => {
        const inpFromUser = inputY.value.trim();
        if(inpFromUser === '') {
            checkY = false;
            labelY.classList.remove('valid', 'invalid');
        } else if(validateNumberInRange(inpFromUser, yStart, yEnd)) {
            checkY = true;
            labelY.classList.add('valid');
            labelY.classList.remove('invalid');
        } else {
            checkY = false;
            labelY.classList.add('invalid');
            labelY.classList.remove('valid');
        }
    });
}

function clearStatuses() {
    labelX.classList.remove('valid', 'invalid');
    labelY.classList.remove('valid', 'invalid');
    labelR.classList.remove('valid', 'invalid');
}

validateY();

xOptions.addEventListener('change', (e) =>
{
    if(!e.target.matches('input[type="checkbox"][name="x"]')) return;
    const numOfXSelected = NumberOfXSelected();
    if(numOfXSelected > 1) {
        checkX = false;
        labelX.classList.remove('valid');
        labelX.classList.add('invalid');
    } else if(numOfXSelected === 1) {
        checkX = true;
        labelX.classList.remove('invalid');
        labelX.classList.add('valid');
    } else {
        checkX = false;
        labelX.classList.remove('valid', 'invalid');
    }
});

RBlock.addEventListener('change', (e) => {
    if(!e.target.matches('input[type="radio"][name="R"]')) return;
    const checked = document.querySelector('input[name="R"]:checked');
    checkR = !!checked;
    if (checkR === true) {
        labelR.classList.add('valid');
    }
});

form.addEventListener('submit', async(e) => {
    e.preventDefault();
    clearStatuses();
    if (!checkX || !checkY || !checkR) {
        if (!checkX) labelX.classList.add('invalid');
        else labelX.classList.add('valid');
        if (!checkY) labelY.classList.add('invalid');
        else labelY.classList.add('valid');
        if (!checkR) labelR.classList.add('invalid');
        else labelR.classList.add('valid');
        return;
    }
    const params = new URLSearchParams(new FormData(form)).toString();
    const url = form.action + '?' + params;
    console.log(url);
    fetch(url,{
        headers: {'Accept': 'application/json'}
    }).then(resp => {
        if(!resp.ok) throw new Error('HTTP ' + resp.status);
        return resp.json();
    }).then(data => {
        const newRow = resTable.insertRow(1);
        newRow.innerHTML = `
              <td>${data.hit ? 'Yes' : 'No'}</td>
              <td>${data.x}</td>
              <td>${data.y}</td>
              <td>${data.R}</td>
              <td>${data.calTime}</td>
              <td>${data.printTime}</td>`;
    }).catch(err => {
        console.error('Fetch Error: ', err);
        alert('Error in server calling: ' + err.message);
    })
});

// document.getElementById('submit-button').addEventListener('pointerover', function() {
//     this.textContent = 'Submit ➜';
// });
// document.getElementById('submit-button').addEventListener('pointerleave', function() {
//     this.textContent = 'Submit';
// });