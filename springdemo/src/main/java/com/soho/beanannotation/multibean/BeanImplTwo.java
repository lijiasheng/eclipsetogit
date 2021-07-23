package com.soho.beanannotation.multibean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("beanImplTwo")
public class BeanImplTwo implements BeanInterface {

}
