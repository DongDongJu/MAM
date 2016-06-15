<?php
$con = mysqli_connect("127.0.0.1","root","qkrtpwlsWkd","MAM");

mysqli_set_charset($con,"utf8");

if(mysqli_connect_errno($con))
{
        echo "Failed : ". mysqli_connect_error();
}

$userName = $_GET['userName'];
$songName = $_GET['songName'];

$result = mysqli_query($con,"insert into selectSongList (userName,songName) values ('$userName','$songName')");

        if($result){
                echo 'success';
        }
        else{
                echo 'fail';
        }

mysqli_close($con);
?>

