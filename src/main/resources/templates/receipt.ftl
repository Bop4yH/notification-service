<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Квитанция</title>
    <style>
        body { font-family: sans-serif; border: 1px solid #ccc; padding: 20px; width: 600px; margin: 0 auto; }
        .header { text-align: center; color: #0055a5; }
        .row { display: flex; justify-content: space-between; border-bottom: 1px solid #eee; padding: 10px 0; }
        .footer { margin-top: 20px; font-size: 0.8em; color: gray; text-align: center; }
    </style>
</head>
<body>
    <div class="header">
        <h1>КВИТАНЦИЯ</h1>
        <h3>Wallet Bank</h3>
    </div>

    <div class="row">
        <b>ID транзакции:</b> <span>${transferId}</span>
    </div>
    <div class="row">
        <b>Дата:</b> <span>${date}</span>
    </div>
    <hr/>
    <div class="row">
        <b>Отправитель:</b> <span>${senderName}</span>
    </div>
    <div class="row">
        <b>Получатель ID:</b> <span>${receiverId}</span>
    </div>
    <div class="row" style="font-size: 1.2em;">
        <b>Сумма:</b> <span>${amount} ${currency}</span>
    </div>

    <div class="footer">
        Электронный документ сформирован автоматически.
    </div>
</body>
</html>