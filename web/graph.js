const canvas = document.getElementById('graph');
const ctx = canvas.getContext('2d');

let RDraw;

function drawTriangle () {
    ctx.beginPath();
    ctx.moveTo(0, 0);
    ctx.lineTo(-RDraw/2, 0);
    ctx.lineTo(0, RDraw/2);
    ctx.closePath();
    ctx.fill();
}

function drawSquare ()   { ctx.fillRect(0, 0, -RDraw/2, -RDraw); }

function drawCircle () {
    ctx.beginPath();
    ctx.moveTo(0, 0);
    ctx.arc(0, 0, RDraw/2, 0, Math.PI/2, false);
    ctx.closePath();
    ctx.fill();
}

function drawLabel(pos, label, axis) {
    const fontSize = RDraw * 0.12;
    ctx.save();
    ctx.scale(1, -1);
    ctx.font = `bold ${fontSize}px sans-serif`;
    ctx.fillStyle = 'rgb(0,0,0)';
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';

    let x, y;
    if (axis === 'x') {
        x = pos;
        y = -25;
    } else {
        x = 25;
        y = -pos;
    }
    ctx.fillText(label, x, y);
    ctx.restore();
}

function drawNet() {
    const left = -canvas.width / 2;
    const right = canvas.width / 2;
    const top = canvas.height / 2;
    const bottom = -canvas.height / 2;

    const step = RDraw / 2;
    const startX = Math.floor(left  / step) * step;
    const endX = Math.ceil (right / step) * step;
    const startY = Math.floor(bottom / step) * step;
    const endY = Math.ceil (top   / step) * step;

    ctx.save();
    ctx.beginPath();
    ctx.setLineDash([5, 10]);
    ctx.lineWidth = 2;

    for (let x = startX; x <= endX; x += step) {
        ctx.moveTo(x, bottom);
        ctx.lineTo(x, top);
    }

    for (let y = startY; y <= endY; y += step) {
        ctx.moveTo(left, y);
        ctx.lineTo(right, y);
    }

    ctx.stroke();
    ctx.setLineDash([]);
    ctx.beginPath();
    ctx.moveTo(left, 0);
    ctx.lineTo( right, 0);
    ctx.moveTo( 0, bottom); ctx.lineTo(0,  top);
    ctx.stroke();
    ctx.restore();
}

function drawGraph() {
    canvas.width  = canvas.clientWidth;
    canvas.height = canvas.clientHeight;

    RDraw = Math.min(canvas.width, canvas.height) * 0.4;

    ctx.translate(canvas.clientWidth / 2, canvas.clientHeight / 2 - 20);
    ctx.scale(1, -1);

    ctx.fillStyle   = 'rgb(1,50,32)';
    ctx.strokeStyle = 'rgba(0,0,0,0.6)';

    drawCircle();
    drawTriangle();
    drawSquare();
    drawNet();

    const marks   = [-RDraw, -RDraw / 2, RDraw / 2, RDraw];
    const symbols = ['-R', '-R/2', 'R/2', 'R'];
    marks.forEach((p, i) => drawLabel(p, symbols[i], 'x'));
    marks.forEach((p, i) => drawLabel(p, symbols[i], 'y'));
}

window.addEventListener('DOMContentLoaded', drawGraph);
window.addEventListener('resize', drawGraph);