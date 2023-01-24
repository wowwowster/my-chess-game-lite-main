import { dataSelector } from '../support/selector';
import { interceptForever } from '../support/Interceptor';

describe('Patch', () => {
  describe('E2E', () => {
    it('Should display modules', () => {
      cy.visit('/patches');

      cy.get(dataSelector('modules-list')).should('be.visible');
    });
  });

  describe('Component', () => {
    beforeEach(() => cy.intercept({ path: '/api/project-folders' }, { body: '/tmp/jhlite/1234' }));

    it('Should display loader while loading modules', () => {
      const result = interceptForever({ path: '/api/modules' }, { fixture: 'modules.json' });

      cy.visit('/patches');

      cy.get(dataSelector('modules-loader'))
        .should('be.visible')
        .then(() => {
          result.send();

          cy.get(dataSelector('modules-loader')).should('not.exist');
          cy.get(dataSelector('modules-list')).should('be.visible');
          cy.get(dataSelector('module-spring-test-application-button')).should('be.enabled');
          cy.get(dataSelector('module-spring-cucumber-application-button')).should('be.enabled');
          cy.get(dataSelector('folder-path-field')).invoke('val').should('equal', '/tmp/jhlite/1234');
        });
    });

    it('Should apply module without properties', () => {
      cy.intercept({ path: '/api/modules' }, { fixture: 'modules.json' });

      cy.intercept({
        path: '/api/modules/spring-test/apply-patch',
        method: 'POST',
      }).as('spring-test-creation');

      cy.visit('/patches');
      cy.get(dataSelector('folder-path-field')).clear().type('test');
      cy.get(dataSelector('module-spring-test-application-button')).click();

      cy.wait('@spring-test-creation').should(xhr => {
        const body = xhr.request.body;

        expect(body).to.deep.equal({
          projectFolder: 'test',
          commit: true,
          parameters: {},
        });
      });
    });

    it('Should apply module with properties', () => {
      cy.intercept({ path: '/api/modules' }, { fixture: 'modules.json' });

      cy.intercept({
        path: '/api/modules/spring-cucumber/apply-patch',
        method: 'POST',
      }).as('spring-cucumber-creation');

      cy.visit('/patches');

      cy.get(dataSelector('spring-cucumber-module-content')).click();
      cy.get(dataSelector('folder-path-field')).clear().type('test');
      cy.get(dataSelector('parameter-baseName-field')).type('jhipster');
      cy.get(dataSelector('parameter-optionalBoolean-field')).select('true');
      cy.get(dataSelector('parameter-optionalInteger-field')).type('42');
      cy.get(dataSelector('module-spring-cucumber-application-button')).click();

      cy.wait('@spring-cucumber-creation').should(xhr => {
        const body = xhr.request.body;

        expect(body).to.deep.equal({
          projectFolder: 'test',
          commit: true,
          parameters: {
            baseName: 'jhipster',
            optionalBoolean: true,
            optionalInteger: 42,
          },
        });
      });
    });
  });
});
