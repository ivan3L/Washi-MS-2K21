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

INSERT INTO user_payment_methods (id, user_id, payment_method_id)
VALUES(1, 1, 1);
INSERT INTO user_payment_methods (id, user_id, payment_method_id)
VALUES(2, 2, 1);
INSERT INTO user_payment_methods (id, user_id, payment_method_id)
VALUES(3, 3, 1);
INSERT INTO user_payment_methods (id, user_id, payment_method_id)
VALUES(4, 4, 1);
INSERT INTO user_payment_methods (id, user_id, payment_method_id)
VALUES(5, 5, 1);
INSERT INTO user_payment_methods (id, user_id, payment_method_id)
VALUES(6, 6, 2);
INSERT INTO user_payment_methods (id, user_id, payment_method_id)
VALUES(7, 7, 2);
INSERT INTO user_payment_methods (id, user_id, payment_method_id)
VALUES(8, 8, 2);
INSERT INTO user_payment_methods (id, user_id, payment_method_id)
VALUES(9, 9, 2);
INSERT INTO user_payment_methods (id, user_id, payment_method_id)
VALUES(10, 10, 2);

INSERT INTO subscriptions (id, user_id, plan_id)
VALUES(1, 1, 1);
INSERT INTO subscriptions (id, user_id, plan_id)
VALUES(2, 2, 2);
INSERT INTO subscriptions (id, user_id, plan_id)
VALUES(3, 3, 3);
INSERT INTO subscriptions (id, user_id, plan_id)
VALUES(4, 4, 4);
INSERT INTO subscriptions (id, user_id, plan_id)
VALUES(5, 5, 5);
INSERT INTO subscriptions (id, user_id, plan_id)
VALUES(6, 6, 6);
INSERT INTO subscriptions (id, user_id, plan_id)
VALUES(7, 7, 7);
INSERT INTO subscriptions (id, user_id, plan_id)
VALUES(8, 8, 8);
INSERT INTO subscriptions (id, user_id, plan_id)
VALUES(9, 9, 4);
INSERT INTO subscriptions (id, user_id, plan_id)
VALUES(10, 10, 8);