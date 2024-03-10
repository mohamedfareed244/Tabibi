const selectOption = document.getElementById('selectOption');
const formOption1 = document.getElementById('formOption1');
const formOption2 = document.getElementById('formOption2');

selectOption.addEventListener('change', function() {
  if (selectOption.value === 'option1') {
    formOption1.style.display = 'block';
    formOption2.style.display = 'none';
  } else if (selectOption.value === 'option2') {
    formOption1.style.display = 'none';
    formOption2.style.display = 'block';
  } else {
    formOption1.style.display = 'none';
    formOption2.style.display = 'none';
  }
});

function validateForm() {
  // Reset error messages
  var errorElements = document.querySelectorAll(".error-message");
  for (var i = 0; i < errorElements.length; i++) {
      errorElements[i].innerHTML = "";
  }


  var fname = document.getElementById("Fname").value;
  var lname = document.getElementById("Lname").value;
  var email = document.getElementById("email").value;
  var age = document.getElementById("age").value;
  var address = document.getElementById("address").value;
  var phone = document.getElementById("phone").value;
  var password = document.getElementById("password").value;
  var cpassword = document.getElementById("cpassword").value;
  var otpp = document.getElementById("OTP").value;


  var isValid = true;

  if (fname === "") {
      document.getElementById("fname-error").innerHTML = "First Name is required.";
      isValid = false;
  }
  if (lname === "") {
      document.getElementById("lname-error").innerHTML = "Last Name is required.";
      isValid = false;
  }
  if (email === "") {
      document.getElementById("email-error").innerHTML = "Email is required.";
      isValid = false;
  }
  if (age === "") {
      document.getElementById("age-error").innerHTML = "Age is required.";
      isValid = false;
  }
  if (address === "") {
      document.getElementById("address-error").innerHTML = "Address is required.";
      isValid = false;
  }
  if (phone === "") {
      document.getElementById("phone-error").innerHTML = "Phone Number is required.";
      isValid = false;
  }
  if (password === "") {
      document.getElementById("password-error").innerHTML = "Password is required.";
      isValid = false;
  }
  if (cpassword === "") {
      document.getElementById("cpassword-error").innerHTML = "Confirm Password is required.";
      isValid = false;
  }

  var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
  if (!emailPattern.test(email)) {
      document.getElementById("email-error").innerHTML = "Invalid email address.";
      isValid = false;
  }

  if (password !== cpassword) {
      document.getElementById("cpassword-error").innerHTML = "Passwords do not match.";
      isValid = false;
  }
  if (phone.length <11||phone.length>11) {
    document.getElementById("phone-error").innerHTML = "Please enter a valid number.";
    isValid = false;
}
  if(age<16)
  {
    document.getElementById("age-error").innerHTML = "You have to be older than 16 years old.";
      isValid = false;
  }

  if (password.length <6) {
      document.getElementById("password-error").innerHTML = "Password must be atleast 6 characters.";
      isValid = false;
  }

  return isValid; 

}

