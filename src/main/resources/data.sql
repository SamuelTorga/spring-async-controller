insert into asynccontroller.product (id, default_price, name)
values  (1, 150.00, 'Product 1'),
        (2, 10.00, 'Product 2'),
        (3, 5.00, 'Product 3');

insert into asynccontroller.tag (id, name)
values  (1, 'Tag 1'),
        (2, 'Tag 2');

insert into asynccontroller.product_tags (products_id, tags_id)
values  (1, 1),
        (2, 1),
        (3, 2);