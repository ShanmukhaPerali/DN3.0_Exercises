DECLARE
    CURSOR client_cursor IS
        SELECT ClientID, FullName, BirthDate, AccountBalance, LastUpdate
        FROM Clients;

    client_rec client_cursor%ROWTYPE;
BEGIN
    OPEN client_cursor;
    
    LOOP
        FETCH client_cursor INTO client_rec;
        EXIT WHEN client_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('ClientID: ' || client_rec.ClientID || 
                             ', FullName: ' || client_rec.FullName || 
                             ', BirthDate: ' || client_rec.BirthDate || 
                             ', AccountBalance: ' || client_rec.AccountBalance || 
                             ', LastUpdate: ' || client_rec.LastUpdate);
    END LOOP;
    
    CLOSE client_cursor;
END;
/
