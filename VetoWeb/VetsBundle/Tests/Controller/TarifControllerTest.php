<?php

namespace VetsBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class TarifControllerTest extends WebTestCase
{
    public function testListtarif()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/listTarif');
    }

}
