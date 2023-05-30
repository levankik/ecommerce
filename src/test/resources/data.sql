-- Passwords are in the format: Password<UserLetter>123, unless specified otherwise.
-- Encrypted using https://www.javainuse.com/onlineBcrypt

INSERT INTO local_user (email, first_name, last_name, password, username, email_verified)
VALUES ('UserA@junit.com', 'UserA-FirstName', 'UserA-LastName', '$2a$10$et4BlTILbXfKWqlwtaaS6.uyEXpMmrxBpAySumwuFvoNAP3z7rS.2', 'UserA', true),
      ('UserB@junit.com', 'UserB-FirstName', 'UserB-LastName', '$2a$10$miPVZx1PcaQpI4pc7kFzCeJP1FhJ9WP0UxZsmJk0UGFlDKVryMbGm', 'UserB', false);
