const client = filestack.init(fileStackAPI);

$('#addPicture').click((event) =>{
    event.preventDefault();
    client.picker().open();
})