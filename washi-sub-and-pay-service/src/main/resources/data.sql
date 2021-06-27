INSERT INTO payment_methods (id, name)
VALUES(1, 'Visa');
INSERT INTO payment_methods (id, name)
VALUES(2, 'MasterCard');

INSERT INTO plans (id, name, price, duration_in_days)
VALUES(1, 'Washer Plus Mensual', 10, 31);
INSERT INTO plans (id, name, price, duration_in_days)
VALUES(2, 'Washer Gold Mensual', 20, 31);
INSERT INTO plans (id, name, price, duration_in_days)
VALUES(3, 'Lavandería Plus Mensual', 100, 31);
INSERT INTO plans (id, name, price, duration_in_days)
VALUES(4, 'Lavandería Gold Mensual', 200, 31);
INSERT INTO plans (id, name, price, duration_in_days)
VALUES(5, 'Washer Plus Anual', 100, 365);
INSERT INTO plans (id, name, price, duration_in_days)
VALUES(6, 'Washer Gold Anual', 200, 365);
INSERT INTO plans (id, name, price, duration_in_days)
VALUES(7, 'Lavandería Plus Anual', 1000, 365);
INSERT INTO plans (id, name, price, duration_in_days)
VALUES(8, 'Lavandería Gold Anual', 2000, 365);

INSERT INTO user_payment_methods (id, number_user_payment_method, user_id, payment_method_id, state)
VALUES(1, '0001', 1, 1, 'CREATED');
INSERT INTO user_payment_methods (id, number_user_payment_method, user_id, payment_method_id, state)
VALUES(2, '0002', 2, 1, 'CREATED');
INSERT INTO user_payment_methods (id, number_user_payment_method, user_id, payment_method_id, state)
VALUES(3, '0003', 3, 1, 'CREATED');
INSERT INTO user_payment_methods (id, number_user_payment_method, user_id, payment_method_id, state)
VALUES(4, '0004', 4, 1, 'CREATED');
INSERT INTO user_payment_methods (id, number_user_payment_method, user_id, payment_method_id, state)
VALUES(5, '0005', 5, 1, 'CREATED');
INSERT INTO user_payment_methods (id, number_user_payment_method, user_id, payment_method_id, state)
VALUES(6, '0006', 6, 2, 'CREATED');
INSERT INTO user_payment_methods (id, number_user_payment_method, user_id, payment_method_id, state)
VALUES(7, '0007', 7, 2, 'CREATED');
INSERT INTO user_payment_methods (id, number_user_payment_method, user_id, payment_method_id, state)
VALUES(8, '0008', 8, 2, 'CREATED');
INSERT INTO user_payment_methods (id, number_user_payment_method, user_id, payment_method_id, state)
VALUES(9, '0009', 9, 2, 'CREATED');
INSERT INTO user_payment_methods (id, number_user_payment_method, user_id, payment_method_id, state)
VALUES(10, '0010', 10, 2, 'CREATED');

INSERT INTO subscriptions (id, number_subscription, user_id, plan_id, state)
VALUES(1, '0001', 1, 1, 'CREATED');
INSERT INTO subscriptions (id, number_subscription, user_id, plan_id, state)
VALUES(2, '0002', 2, 2, 'CREATED');
INSERT INTO subscriptions (id, number_subscription, user_id, plan_id, state)
VALUES(3, '0003', 3, 3, 'CREATED');
INSERT INTO subscriptions (id, number_subscription, user_id, plan_id, state)
VALUES(4, '0004', 4, 4, 'CREATED');
INSERT INTO subscriptions (id, number_subscription, user_id, plan_id, state)
VALUES(5, '0005', 5, 5, 'CREATED');
INSERT INTO subscriptions (id, number_subscription, user_id, plan_id, state)
VALUES(6, '0006', 6, 6, 'CREATED');
INSERT INTO subscriptions (id, number_subscription, user_id, plan_id, state)
VALUES(7, '0007', 7, 7, 'CREATED');
INSERT INTO subscriptions (id, number_subscription, user_id, plan_id, state)
VALUES(8, '0008', 8, 8, 'CREATED');
INSERT INTO subscriptions (id, number_subscription, user_id, plan_id, state)
VALUES(9, '0009', 9, 4, 'CREATED');
INSERT INTO subscriptions (id, number_subscription, user_id, plan_id, state)
VALUES(10, '0010', 10, 8, 'CREATED');