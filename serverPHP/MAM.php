<?php
$times = $_GET['times'];
$userName = $_GET['userName'];

echo "$times";
$ps = shell_exec("python /home/ubuntu/musicController/music-rnn/rnn_lstm_jzs1/train.py $times &");

?>

