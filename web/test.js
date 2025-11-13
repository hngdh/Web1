function submitToServer(x, y, r) {
    const yInput = document.getElementById('corY');
    yInput.value = y;
    yInput.dispatchEvent(new Event('input', { bubbles: true }));

    document.querySelectorAll('input[name="x"]').forEach(checkbox => {
        checkbox.checked = false;
    });
    const xCheckbox = document.querySelector(`input[name="x"][value="${x}"]`);
    if (xCheckbox) {
        xCheckbox.checked = true;
        xCheckbox.dispatchEvent(new Event('change', { bubbles: true }));
    }

    const rRadio = document.querySelector(`input[name="R"][value="${r}"]`);
    if (rRadio) {
        rRadio.checked = true;
        rRadio.dispatchEvent(new Event('change', { bubbles: true }));
    }
    setTimeout(() => document.getElementById('submit-button').click(), 6);
}

function testMultipleValues() {
    let delay = 0;
    for (let x = -2; x <= 2; x+=0.5) {
        for (let y = -5; y <= 5; y+=0.1) {
            y = Math.round(y*10)/10;
            for (let r = 1; r <= 5; r++) {
                delay += 10;
                setTimeout(() => submitToServer(x, y, r), delay);
            }
        }
    }
}