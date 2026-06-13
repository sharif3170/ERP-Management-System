const TRANSACTION_API = "http://localhost:8080/api/transactions";
let editTransactionId = null;
const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));

function saveTransaction() {
    const transaction = {
        productId: document.getElementById("productId").value,
        transactionType: document.getElementById("transactionType").value,
        quantity: document.getElementById("quantity").value
    };
    
    let method = "POST";
    let url = TRANSACTION_API;
    
    if (editTransactionId !== null) {
        method = "PUT";
        url = `${TRANSACTION_API}/${editTransactionId}`;
    }
    
    fetch(url, {
        method: method,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(transaction)
    })
    .then(() => {
        loadTransactions();
        clearTransactionForm();
        editTransactionId = null;
        document.getElementById("transactionBtn").innerText = "Save Transaction";
    });
}

function loadTransactions() {
    fetch(TRANSACTION_API)
        .then(res => res.json())
        .then(data => {
            let table = document.getElementById("transactionTable");
            table.innerHTML = "";
            data.forEach(transaction => {
                let dateStr = transaction.transactionDate ? new Date(transaction.transactionDate).toLocaleString() : 'N/A';
                table.innerHTML += `
                    <tr>
                        <td>${transaction.transactionId}</td>
                        <td>${transaction.productId || 'N/A'}</td>
                        <td>${transaction.transactionType || ''}</td>
                        <td>${transaction.quantity || 0}</td>
                        <td>${dateStr}</td>
                        <td>
                            ${
                                loggedInUser && loggedInUser.role.toUpperCase() === "ADMIN" ?
                                `<button onclick="editTransaction(${transaction.transactionId})">Edit</button>
                                 <button onclick="deleteTransaction(${transaction.transactionId})">Delete</button>` :
                                `<span>View Only</span>`
                            }
                        </td>
                    </tr>`;
            });
        });
}

if (loggedInUser && loggedInUser.role.toUpperCase() !== "ADMIN") {
    const tForm = document.getElementById("transactionForm");
    if(tForm) {
        tForm.style.display="none";
    }
}

function editTransaction(id) {
    fetch(TRANSACTION_API)
        .then(res => res.json())
        .then(data => {
            const transaction = data.find(t => t.transactionId === id);
            document.getElementById("productId").value = transaction.productId || '';
            document.getElementById("transactionType").value = transaction.transactionType || '';
            document.getElementById("quantity").value = transaction.quantity || '';
            editTransactionId = id;
            document.getElementById("transactionBtn").innerText = "Update Transaction";
        });
}

function deleteTransaction(id) {
    if (confirm("Delete Transaction Record?")) {
        fetch(`${TRANSACTION_API}/${id}`, {
            method: "DELETE"
        })
        .then(() => {
            loadTransactions();
        });
    }
}

function clearTransactionForm() {
    document.getElementById("productId").value = "";
    document.getElementById("transactionType").value = "";
    document.getElementById("quantity").value = "";
}

loadTransactions();
