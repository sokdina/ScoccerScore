/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.soccerrecordspersistence.dao;

import cz.fi.muni.pa165.soccerrecordspersistence.dao.IPlayerDao;
import cz.fi.muni.pa165.soccerrecordspersistence.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.soccerrecordspersistence.entity.Player;
import cz.fi.muni.pa165.soccerrecordspersistence.enums.Position;
import java.util.Date;
import junit.framework.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Peter Lipcak
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PlayerDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private IPlayerDao playerDao;

    @Test
    public void testCreate(){
        System.out.println("testCreate started");
        Player p1 = new Player();
        
        p1.setName("Ronaldo");
        p1.setPosition(Position.FORWARD);
        p1.setDateOfBirth(new Date(System.currentTimeMillis()));
        p1.setDressNumber(7);
        p1.setCountry("Portugal");
        
        playerDao.create(p1);
        
        System.out.println(playerDao.findById(p1.getId()));
        Assert.assertNotNull("Player was not created!!", playerDao.findById(p1.getId()));
    }
    
    @Test
    public void testDelete(){
        System.out.println("testDelete started");
        Player p1 = new Player();
        
        p1.setName("Ronaldo");
        p1.setPosition(Position.FORWARD);
        p1.setDateOfBirth(new Date(System.currentTimeMillis()));
        p1.setDressNumber(7);
        p1.setCountry("Portugal");
        
        playerDao.create(p1);
        
        long id = p1.getId();
        Assert.assertNotNull("Player was not created!!", playerDao.findById(id));
        playerDao.delete(p1);
        Assert.assertNull("Player was not deleted", playerDao.findById(id));
    }

    @Test
    public void testFindAll(){
        System.out.println("testFindAll started");
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();

        p1.setName("John Terry");
        p1.setPosition(Position.DEFENDER);
        p1.setDateOfBirth(new Date(System.currentTimeMillis()));
        p1.setDressNumber(26);
        p1.setCountry("England");

        p2.setName("Rooney");
        p2.setPosition(Position.FORWARD);
        p2.setDateOfBirth(new Date(System.currentTimeMillis()));
        p2.setDressNumber(25);
        p2.setCountry("England");
        
        p3.setName("Vardy");
        p3.setPosition(Position.MIDFIELDER);
        p3.setDateOfBirth(new Date(System.currentTimeMillis()));
        p3.setDressNumber(20);
        p3.setCountry("England");
        
        playerDao.create(p1);
        playerDao.create(p2);
        playerDao.create(p3);
        
        
        Assert.assertEquals("Size is not 3", 3, playerDao.findAll().size());
        
        playerDao.delete(p1);
        playerDao.delete(p2);
        playerDao.delete(p3);
        
        Assert.assertEquals("Size is not 0", 0, playerDao.findAll().size());
    }
    
    @Test
    public void testFindById(){
    System.out.println("testFindById started");
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();

        p1.setName("John Terry");
        p1.setPosition(Position.DEFENDER);
        p1.setDateOfBirth(new Date(System.currentTimeMillis()));
        p1.setDressNumber(26);
        p1.setCountry("England");

        p2.setName("Rooney");
        p2.setPosition(Position.FORWARD);
        p2.setDateOfBirth(new Date(System.currentTimeMillis()));
        p2.setDressNumber(25);
        p2.setCountry("England");
        
        p3.setName("Vardy");
        p3.setPosition(Position.MIDFIELDER);
        p3.setDateOfBirth(new Date(System.currentTimeMillis()));
        p3.setDressNumber(20);
        p3.setCountry("England");
        
        playerDao.create(p1);
        playerDao.create(p2);
        playerDao.create(p3);
        
        Assert.assertEquals("Players are not equal", p1, playerDao.findById(p1.getId()));
        Assert.assertEquals("Players are not equal", p2, playerDao.findById(p2.getId()));
        Assert.assertEquals("Players are not equal", p3, playerDao.findById(p3.getId()));
    }
    
    @Test
    public void testUpdate(){
        System.out.println("testFindById started");
        Player p1 = new Player();

        p1.setName("John Terry");
        p1.setPosition(Position.DEFENDER);
        p1.setDateOfBirth(new Date(System.currentTimeMillis()));
        p1.setDressNumber(26);
        p1.setCountry("England");
        
        String playerName = "Ronaldo";
        playerDao.create(p1);
        p1.setName(playerName);
        playerDao.update(p1);
        
        Player p2 = playerDao.findById(p1.getId());
        Assert.assertTrue("Names are equal --> not updated!!", p2.getName().compareTo(playerName) == 0);
    }
    
    @Test
    public void testFindByName(){
        System.out.println("testFindByName started");
        Player p1 = new Player();

        p1.setName("Ronaldo");
        p1.setPosition(Position.DEFENDER);
        p1.setDateOfBirth(new Date(System.currentTimeMillis()));
        p1.setDressNumber(26);
        p1.setCountry("England");
        
        playerDao.create(p1);
        
        Assert.assertTrue(playerDao.findByName("Ronaldo").size() == 1);
    }
    
}
