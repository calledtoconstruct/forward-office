ALTER TABLE entry DROP CONSTRAINT valid_url;
ALTER TABLE entry ADD CONSTRAINT valid_url CHECK (url ~* '^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))(%[0-9A-Fa-f]{2}|[-()_.!~*'';/?:@#&=+$,A-Za-z0-9])+)([).!'';/?:,])?$');
INSERT INTO Entry (name, url) VALUES ('equifax', 'https://my.equifax.com/membercenter/#/login');
