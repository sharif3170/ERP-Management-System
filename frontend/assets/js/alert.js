const ALERT_API = "http://localhost:8080/api/alerts";
let editAlertId = null;
const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));

function saveAlert() {
    const alertData = {
        productId: document.getElementById("productId").value,
        alertMessage: document.getElementById("alertMessage").value
    };
    
    let method = "POST";
    let url = ALERT_API;
    
    if (editAlertId !== null) {
        method = "PUT";
        url = `${ALERT_API}/${editAlertId}`;
    }
    
    fetch(url, {
        method: method,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(alertData)
    })
    .then(() => {
        loadAlerts();
        clearAlertForm();
        editAlertId = null;
        document.getElementById("alertBtn").innerText = "Save Alert";
    });
}

function loadAlerts() {
    fetch(ALERT_API)
        .then(res => res.json())
        .then(data => {
            let table = document.getElementById("alertTable");
            table.innerHTML = "";
            data.forEach(alert => {
                let dateStr = alert.alertDate ? new Date(alert.alertDate).toLocaleString() : 'N/A';
                table.innerHTML += `
                    <tr>
                        <td>${alert.alertId}</td>
                        <td>${alert.productId || 'N/A'}</td>
                        <td>${alert.alertMessage || ''}</td>
                        <td>${dateStr}</td>
                        <td>
                            ${
                                loggedInUser && loggedInUser.role.toUpperCase() === "ADMIN" ?
                                `<button onclick="editAlert(${alert.alertId})">Edit</button>
                                 <button onclick="deleteAlert(${alert.alertId})">Delete</button>` :
                                `<span>View Only</span>`
                            }
                        </td>
                    </tr>`;
            });
        });
}

if (loggedInUser && loggedInUser.role.toUpperCase() !== "ADMIN") {
    const aForm = document.getElementById("alertForm");
    if(aForm) {
        aForm.style.display="none";
    }
}

function editAlert(id) {
    fetch(ALERT_API)
        .then(res => res.json())
        .then(data => {
            const alert = data.find(a => a.alertId === id);
            document.getElementById("productId").value = alert.productId || '';
            document.getElementById("alertMessage").value = alert.alertMessage || '';
            editAlertId = id;
            document.getElementById("alertBtn").innerText = "Update Alert";
        });
}

function deleteAlert(id) {
    if (confirm("Delete Alert Record?")) {
        fetch(`${ALERT_API}/${id}`, {
            method: "DELETE"
        })
        .then(() => {
            loadAlerts();
        });
    }
}

function clearAlertForm() {
    document.getElementById("productId").value = "";
    document.getElementById("alertMessage").value = "";
}

loadAlerts();
