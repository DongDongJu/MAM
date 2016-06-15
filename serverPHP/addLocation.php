<?php
$con = mysqli_connect("127.0.0.1","root","qkrtpwlsWkd","DongDongLogger");

mysqli_set_charset($con,"utf8");

if(mysqli_connect_errno($con))
{
        echo "Failed : ". mysqli_connect_error();
}

$lat = $_POST['lat'];
$longi = $_POST['longi'];
$place = $_POST['place'];

$result = mysqli_query($con,"insert into Logger (place,lat,longi) values ('$place','$lat','$longi')");

        if($result){
                echo 'success';
        }
        else{
                echo 'fail';
        }
mysqli_close($con);
?>

