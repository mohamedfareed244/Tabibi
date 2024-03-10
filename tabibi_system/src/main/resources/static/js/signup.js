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