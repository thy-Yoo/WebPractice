create table membership_benefit(
    mno NUMBER,
    userid VARCHAR2(30 byte), -- member 테이블에서 비교할 값.
    usergrade VARCHAR2(30 byte) DEFAULT '일반' NOT NULL, 
    total_point NUMBER DEFAULT 0 NOT NULL, 
    total_ticketnums NUMBER DEFAULT 0 NOT NULL,
    /*NUMBER의 경우 moviefree+storefree+storediscount+photoplay+50discount 의 값들 더하기 =>temp에 넣고
        -- if(vip_double1==true){ temp++;}
        -- if(vip_double2==true){temp++;}
        -- if(specialday==true){temp++;}
        --if(birthday==true){temp++;}
        --if(specialgift1==true){temp++;}
        --if(specialgift2==true){temp++;}
        --if(svip_room==true){temp++;}*/
        
    /*쿠폰들*/
    online_moviefree NUMBER DEFAULT 0 NOT NULL,
    online_storefree NUMBER DEFAULT 0 NOT NULL,
    online_storediscount NUMBER DEFAULT 0 NOT NULL,
    online_photoplay NUMBER DEFAULT 0 NOT NULL,
    point_50discount NUMBER DEFAULT 0 NOT NULL,
    vip_double1 NUMBER DEFAULT 0 NOT NULL, /*0 or 3퍼센트, 상영일 이전예매*/
    vip_double2 NUMBER DEFAULT 0 NOT NULL, /*0 or 7퍼센트 , 상영일 이후예매*/
    vip_refill NUMBER DEFAULT 0 NOT NULL, /*몇퍼센트인지, */
    specialday VARCHAR(20) DEFAULT '없음' NOT NULL, /*에이드 2잔무료 쿠폰이 있는지, 없는지,*/ 
    birthday VARCHAR(20) DEFAULT '있음' NOT NULL, /*생일무료콤보 쿠폰이 있는지, 없는지,*/
    specialgift1 VARCHAR(20) DEFAULT '없음' NOT NULL, /*스페셜기프트쿠폰(SVIP스페셜기프트)이 있는지, 없는지*/
    specialgift2 VARCHAR(20) DEFAULT '없음' NOT NULL, /*스페셜기프트쿠폰(SVIP원데이프리패스)이 있는지, 없는지*/
    svip_room VARCHAR(20) DEFAULT '있음' NOT NULL, /*특별관 1만원 발권쿠폰이 있는지, 없는지   */
    vip_event VARCHAR(20) DEFAULT '참여불가능' NOT NULL  /*VIP 전용 이벤트 참여 가능한지 아닌지. */ 
);