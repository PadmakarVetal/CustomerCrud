const BASE_URL = "https://customercrud-jyzl.onrender.com/customers";

// Load all customers
function loadCustomers() {
  fetch(BASE_URL)
    .then(res => res.json())
    .then(data => {
      let rows = "";
      data.forEach(c => {
        rows += `
          <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>${c.mob}</td>
            <td>${c.address}</td>
            <td>${c.total}</td>
            <td>
              <a href="edit.html?id=${c.id}" class="btn btn-sm btn-warning">Edit</a>
              <button class="btn btn-sm btn-danger" onclick="deleteCustomer(${c.id})">Delete</button>
            </td>
          </tr>
        `;
      });
      document.getElementById("customerTable").innerHTML = rows;
    });
}

// Add customer
function addCustomer() {
  const customer = {
    id: id.value,
    name: name.value,
    mob: mob.value,
    address: address.value,
    total: total.value
  };

  fetch(BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(customer)
  }).then(() => window.location.href = "index.html");
}

// Delete customer
function deleteCustomer(id) {
  fetch(`${BASE_URL}/${id}`, { method: "DELETE" })
    .then(() => loadCustomers());
}

// Load customer by id
function loadCustomerById() {
  const idParam = new URLSearchParams(window.location.search).get("id");

  fetch(`${BASE_URL}/${idParam}`)
    .then(res => res.json())
    .then(c => {
      id.value = c.id;
      name.value = c.name;
      mob.value = c.mob;
      address.value = c.address;
      total.value = c.total;
    });
}

// Update customer
function updateCustomer() {
  const customer = {
    name: name.value,
    mob: mob.value,
    address: address.value,
    total: total.value
  };

  fetch(`${BASE_URL}/${id.value}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(customer)
  }).then(() => window.location.href = "index.html");
}
