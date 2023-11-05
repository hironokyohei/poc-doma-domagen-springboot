insert into
    users
(id, name, address)
values
/*%for user : users */
(
    /* user.id */1,
    /* user.name */'テスト名前',
    /* user.address */'テスト住所'
)
    /*%if user_has_next */
        /*# "," */
    /*%end */
/*%end*/