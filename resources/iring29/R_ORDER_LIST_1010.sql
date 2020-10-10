--------------------------------------------------------
--  DDL for Table R_ORDER_LIST
--------------------------------------------------------

  CREATE TABLE "III_TEAM_DBA"."R_ORDER_LIST" 
   (  "R_SN_ORDER" NUMBER, 
  "ORDER_ID" NUMBER, 
  "R_SN" NUMBER, 
  "CUSTOMER_NUM" NUMBER(3,0), 
  "BOOK_TIME" TIMESTAMP (6), 
  "DEPOSIT" NUMBER, 
  "CUS_NAME" VARCHAR2(255 BYTE), 
  "CUS_PHONE" VARCHAR2(255 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index R_ORDER_LIST_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "III_TEAM_DBA"."R_ORDER_LIST_PK" ON "III_TEAM_DBA"."R_ORDER_LIST" ("R_SN_ORDER") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Sequence R_ORDER_LIST_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "III_TEAM_DBA"."R_ORDER_LIST_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

--------------------------------------------------------
--  DDL for Trigger R_ORDER_LIST_TRG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "III_TEAM_DBA"."R_ORDER_LIST_TRG" 
BEFORE INSERT ON R_ORDER_LIST 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.R_SN_ORDER IS NULL THEN
      SELECT R_ORDER_LIST_SEQ.NEXTVAL INTO :NEW.R_SN_ORDER FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "III_TEAM_DBA"."R_ORDER_LIST_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table R_ORDER_LIST
--------------------------------------------------------

  ALTER TABLE "III_TEAM_DBA"."R_ORDER_LIST" MODIFY ("R_SN_ORDER" NOT NULL ENABLE);
  ALTER TABLE "III_TEAM_DBA"."R_ORDER_LIST" ADD CONSTRAINT "R_ORDER_LIST_PK" PRIMARY KEY ("R_SN_ORDER")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table R_ORDER_LIST
--------------------------------------------------------

  ALTER TABLE "III_TEAM_DBA"."R_ORDER_LIST" ADD CONSTRAINT "R_ORDER_LIST_FK1" FOREIGN KEY ("ORDER_ID")
    REFERENCES "III_TEAM_DBA"."ORDER_TABLE" ("ORDER_ID") ENABLE;
  ALTER TABLE "III_TEAM_DBA"."R_ORDER_LIST" ADD CONSTRAINT "R_ORDER_LIST_FK2" FOREIGN KEY ("R_SN")
    REFERENCES "III_TEAM_DBA"."RESTAURANT" ("R_SN") ENABLE;
